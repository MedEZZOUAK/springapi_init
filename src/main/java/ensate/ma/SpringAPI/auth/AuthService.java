package ensate.ma.SpringAPI.auth;


import ensate.ma.SpringAPI.Exception.EmailAlreadyExistsException;
import ensate.ma.SpringAPI.Exception.InvalidCredentialsException;
import ensate.ma.SpringAPI.Model.Candidat;
import ensate.ma.SpringAPI.Model.Professeur;
import ensate.ma.SpringAPI.Repository.CandidatRepo;
import ensate.ma.SpringAPI.Repository.ProfesseurRepo;
import ensate.ma.SpringAPI.config.JwtService;
import ensate.ma.SpringAPI.user.Role;
import ensate.ma.SpringAPI.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ensate.ma.SpringAPI.Repository.loginRepo;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {
  private final loginRepo loginRepo;
  private final PasswordEncoder passwordEncoder;
  private final CandidatRepo candidatRepo;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;
  private final ProfesseurRepo profRepo;


  public AuthenticationResponse register(RegisterRequest registerRequest) {
    // Check if the email already exists : if it does, throw an exception
    if (loginRepo.findByEmail(registerRequest.getEmail()).isPresent()) {
      throw new EmailAlreadyExistsException("Email already exists");
    }
    log.info("Register request: {}", registerRequest.getCIN());
    var candidat = Candidat.builder()
      .nom(registerRequest.getNom())
      .prenom(registerRequest.getPrenom())
      .CIN(registerRequest.getCIN())
      .telephone(registerRequest.getTelephone())
      .email(registerRequest.getEmail())
      .build();
    log.info("New candidat: {}", candidat);
    candidatRepo.save(candidat);
    var user = User.builder()
      .email(registerRequest.getEmail())
      .password(passwordEncoder.encode(registerRequest.getPassword()))
      .role(Role.Candidat)
      .build();
    loginRepo.save(user);
    var jwtToken= jwtService.generateToken(user);
    return AuthenticationResponse.builder()
      .Token(jwtToken)
      .role(user.getRole())
      .build();
  }

  public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
    try {
      authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword())
      );
    }catch (Exception e) {
      throw new InvalidCredentialsException("Invalid credentials");
    }
    var user = loginRepo.findByEmail(authenticationRequest.getEmail())
      .orElseThrow(() -> new InvalidCredentialsException("User not found"));
    var jwtToken = jwtService.generateToken(user);
    Integer userId = null;
    switch (user.getRole()) {
      case Candidat:

        Candidat candidat = candidatRepo.findByEmail(user.getEmail())
          .orElseThrow(() -> new InvalidCredentialsException("Candidat not found"));
        userId= Math.toIntExact(candidat.getId());
        break;
      case Professeur:
        Professeur prof = profRepo.findByEmail(user.getEmail())
          .orElseThrow(() -> new InvalidCredentialsException("Candidat not found"));
        userId= Math.toIntExact(prof.getId());
        break;
    }

    return AuthenticationResponse.builder()
      .Token(jwtToken)
      .role(user.getRole())
      .id(userId)
      .build();
  }
}
