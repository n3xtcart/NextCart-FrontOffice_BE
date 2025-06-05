package it.nextre.nextcart.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import it.nextre.aut.dto.LoginInfo;
import it.nextre.aut.dto.TokenJwtDTO;
import it.nextre.aut.dto.UserDTO;
import it.nextre.aut.service.UserService;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserServiceImpl implements UserService {

    private final Map<String, UserDTO> userDatabase = new HashMap<>();

	@Override
	public TokenJwtDTO login(LoginInfo loginInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TokenJwtDTO register(UserDTO userDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(UserDTO userDTO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(UserDTO userDTO) {
		// TODO Auto-generated method stub
		
	}

    /*@Override
    public String login(LoginInfo loginInfo) {
        UserDTO user = userDatabase.get(loginInfo.getEmail());

        if (user == null) {
            return "Utente non trovato";
        }

        if (!Objects.equals(user.getPassword(), loginInfo.getPassword())) {
            return "Password errata";
        }

        return "Login riuscito per: " + user.getNome() + " " + user.getCognome();
    }

    @Override
    public String register(UserDTO userDTO) {
        if (userDatabase.containsKey(userDTO.getEmail())) {
            return "Utente già registrato con questa email";
        }

        if (userDTO.getPassword() == null || userDTO.getPassword().isBlank()) {
            return "La password è obbligatoria";
        }

        userDTO.setId(generateId());
        userDatabase.put(userDTO.getEmail(), userDTO);
        return "Registrazione completata con successo";
    }

    @Override
    public void update(UserDTO userDTO) {
        if (!userDatabase.containsKey(userDTO.getEmail())) {
            throw new RuntimeException("Utente non trovato");
        }

        UserDTO esistente = userDatabase.get(userDTO.getEmail());

        if (userDTO.getPassword() == null || userDTO.getPassword().isBlank()) {
            userDTO.setPassword(esistente.getPassword());
        }

        userDTO.setId(esistente.getId());

        userDatabase.put(userDTO.getEmail(), userDTO);
    }

    @Override
    public void delete(UserDTO userDTO) {
        if (!userDatabase.containsKey(userDTO.getEmail())) {
            throw new RuntimeException("Utente non trovato");
        }

        userDatabase.remove(userDTO.getEmail());
    }

    private long generateId() {
        return userDatabase.values().stream()
                .mapToLong(u -> u.getId() != null ? u.getId() : 0)
                .max()
                .orElse(0) + 1;
    }*/
}
