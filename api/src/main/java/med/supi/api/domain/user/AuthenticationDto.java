package med.supi.api.domain.user;

public record AuthenticationDto (
    String login,
    String password
) {
    
}
