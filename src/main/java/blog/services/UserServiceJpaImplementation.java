package blog.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import blog.models.User;
import blog.repositories.UserRepository;

@Service
@Primary
public class UserServiceJpaImplementation implements UserService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public List<User> findAll() {
		return this.userRepository.findAll();
	}

	@Override
	public User findById(Long id) {
		return this.userRepository.findOne(id);
	}

	@Override
	public User create(User user) {
		return this.userRepository.save(user);
	}

	@Override
	public User edit(User user) {
		return this.userRepository.save(user);
	}

	@Override
	public void deleteById(Long id) {
		this.userRepository.delete(id);
	}

}
