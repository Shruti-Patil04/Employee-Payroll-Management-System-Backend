package com.payroll.controller;
import com.payroll.dto.ChangePasswordRequest;
import com.payroll.dto.AuthRequest;
import com.payroll.dto.AuthResponse;
import com.payroll.model.User;
import com.payroll.service.UserService;
import com.payroll.service.WelcomeAIService;
import com.payroll.config.JwtService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import com.payroll.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.payroll.dto.ForgotPasswordRequest;
@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {
	private final WelcomeAIService welcomeAIService;
	private final AuthenticationManager authenticationManager;
	private final JwtService jwtService;
	private final UserService userService;
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	public AuthController(
	        AuthenticationManager authenticationManager,
	        JwtService jwtService,
	        UserService userService,
	        WelcomeAIService welcomeAIService,
	        UserRepository userRepository,
	        PasswordEncoder passwordEncoder) {

	    this.authenticationManager = authenticationManager;
	    this.jwtService = jwtService;
	    this.userService = userService;
	    this.welcomeAIService = welcomeAIService;
	    this.userRepository = userRepository;
	    this.passwordEncoder = passwordEncoder;
	}

	@PostMapping("/login")
	public ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthRequest authRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						authRequest.getUsername(),
						authRequest.getPassword()
						)
				);

		User user = (User) authentication.getPrincipal();
		String jwtToken = jwtService.generateToken(user);

		AuthResponse authResponse = new AuthResponse(
				jwtToken,
				user.getUserId(),
				user.getUsername(),
				user.getEmail(),
				user.getRole().name()
				);

		return ResponseEntity.ok(authResponse);
	}

	@PostMapping("/register")
	public ResponseEntity<AuthResponse> register(@Valid @RequestBody AuthRequest authRequest) {
		User user = userService.createUser(
				authRequest.getUsername(),
				authRequest.getEmail(),
				authRequest.getPassword(),
				User.Role.EMPLOYEE
				);

		String jwtToken = jwtService.generateToken(user);

		AuthResponse authResponse = new AuthResponse(
				jwtToken,
				user.getUserId(),
				user.getUsername(),
				user.getEmail(),
				user.getRole().name()
				);

		return ResponseEntity.ok(authResponse);
	}

	@PostMapping("/register/admin")
	public ResponseEntity<AuthResponse> registerAdmin(@Valid @RequestBody AuthRequest authRequest) {
		User user = userService.createUser(
				authRequest.getUsername(),
				authRequest.getEmail(),
				authRequest.getPassword(),
				User.Role.ADMIN
				);

		String jwtToken = jwtService.generateToken(user);

		AuthResponse authResponse = new AuthResponse(
				jwtToken,
				user.getUserId(),
				user.getUsername(),
				user.getEmail(),
				user.getRole().name()
				);

		return ResponseEntity.ok(authResponse);
	}

	@GetMapping("/me")
	public ResponseEntity<User> getCurrentUser(@AuthenticationPrincipal User user) {
		// Use @AuthenticationPrincipal instead of Authentication parameter
		return ResponseEntity.ok(user);
	}

	@PostMapping("/refresh")
	public ResponseEntity<AuthResponse> refreshToken(Authentication authentication) {
		User user = (User) authentication.getPrincipal();
		String jwtToken = jwtService.generateToken(user);

		AuthResponse authResponse = new AuthResponse(
				jwtToken,
				user.getUserId(),
				user.getUsername(),
				user.getEmail(),
				user.getRole().name()
				);

		return ResponseEntity.ok(authResponse);
	}

	

	@GetMapping("/welcome-message")
	public ResponseEntity<String> getWelcomeMessage(@AuthenticationPrincipal User user) {
	    String aiMessage = welcomeAIService.generateSmartWelcome(user);
	    return ResponseEntity.ok()
	            .header("Content-Type", "text/plain")
	            .body(aiMessage);
	}
	
	@PostMapping("/change-password")
	public ResponseEntity<?> changePassword(
	        @RequestBody ChangePasswordRequest request) {

	    User user = userRepository.findByUsername(request.getUsername())
	            .orElseThrow(() -> new RuntimeException("User not found"));

	    if (!passwordEncoder.matches(
	            request.getOldPassword(),
	            user.getPassword())) {

	        return ResponseEntity.badRequest()
	                .body("Old password is incorrect");
	    }

	    user.setPassword(
	            passwordEncoder.encode(
	                    request.getNewPassword()
	            )
	    );

	    userRepository.save(user);

	    return ResponseEntity.ok("Password changed successfully");
	}
	@PostMapping("/forgot-password")
	public ResponseEntity<?> forgotPassword(
	        @RequestBody ForgotPasswordRequest request) {

	    User user = userRepository
	            .findByUsername(request.getUsername())
	            .orElse(null);

	    if (user == null) {
	        return ResponseEntity.badRequest()
	                .body("User does not exist");
	    }

	    user.setPassword(
	            passwordEncoder.encode(
	                    request.getNewPassword()
	            )
	    );

	    userRepository.save(user);

	    return ResponseEntity.ok(
	            "Password reset successfully"
	    );
	}
}