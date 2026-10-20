package api_teste.ds.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import api_teste.ds.models.User;
import api_teste.ds.repositories.UserRepository;
import api_teste.ds.services.exceptions.DataBindingViolationException;
import api_teste.ds.services.exceptions.ObjectNotFoundException;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User findById(Long id) {
        Optional<User> user = this.userRepository.findById(id);

        return user.orElseThrow(() -> new ObjectNotFoundException(
                "Usuário não encontrado! Id: " + id + ", Tipo: " + User.class.getName()
        ));
    }

    @Transactional
    public User create(User obj) {
        obj.setId(null);
        obj.setPassword(this.bCryptPasswordEncoder.encode(obj.getPassword()));

        obj = this.userRepository.save(obj);

        return obj;
    }

    @Transactional
    public User update(User obj) {
        User newObj = findById(obj.getId());

        newObj.setPassword(this.bCryptPasswordEncoder.encode(obj.getPassword()));

        return this.userRepository.save(newObj);
    }

    public void delete(Long id) {
        findById(id);

        try {
            this.userRepository.deleteById(id);
            this.userRepository.flush();
        } catch (Exception e) {
            throw new DataBindingViolationException(
                    "Não é possível excluir, pois há entidades relacionadas!"
            );
        }
    }
}