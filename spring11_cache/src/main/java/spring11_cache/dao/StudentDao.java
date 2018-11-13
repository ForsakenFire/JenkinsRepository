package spring11_cache.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import spring11_cache.bean.User;

public interface StudentDao extends JpaRepository<User, Integer>{
	User getUserById(int id);
}
