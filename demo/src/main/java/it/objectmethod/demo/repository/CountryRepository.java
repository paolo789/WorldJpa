package it.objectmethod.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import it.objectmethod.demo.entity.CountryBean;
@Repository
public interface CountryRepository  extends JpaRepository<CountryBean, String>{
	
	@Query("select distinct c.continent from CountryBean c")
	public List<String> findAllContinents();
	
	public List<CountryBean> findByContinent(String continent);
	
	public List<CountryBean> findAll();
	
	
}
