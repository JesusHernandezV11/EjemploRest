package edu.ieu.ejemplorest.servicios;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.ieu.ejemplorest.entities.Usuario;
import edu.ieu.ejemplorest.repositorios.UsuarioRepository;

@Service
public class UsuarioServiceImp implements UsuarioService{
	
	@Autowired
	private UsuarioRepository userDao;

	@Override
	public Usuario findById(long id) {
		return userDao.findById((int) id)
				.orElse(null);
		
	}

	@Override
	public Usuario findByNombre(String name) {
		return userDao.findByNombre(name);
		
	}

	@Override
	public List<Usuario> findAll() {
		List<Usuario> lista = new ArrayList<>();
		userDao.findAll()
			.forEach( lista::add );
		return lista;
		
	}

	@Override
	public boolean isUserExist(Usuario user) {
		return (userDao.findByNombre(user.getNombre() ) != null);

	}

	@Override @Transactional
	public void saveUser(Usuario user) {
		userDao.save(user);
		
	}

	@Override @Transactional
	public void updateUser(Usuario user) {
		Usuario userdb = userDao.findById(user.getId() ).orElse(null);
		if(userdb != null) {
			userdb.setApellidos( user.getApellidos() );
			userdb.setEmail( user.getEmail() );
			userdb.setNombre( user.getNombre() );
			userdb.setPassword( user.getPassword() );
			userDao.save(userdb);
		}
	}

	@Override @Transactional
	public void deleteUserById(long id) {
		userDao.deleteById( (int) id );
		
	
	}
	
}
