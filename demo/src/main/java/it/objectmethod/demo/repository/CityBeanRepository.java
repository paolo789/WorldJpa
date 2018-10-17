package it.objectmethod.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.objectmethod.demo.entity.CityBean;
import it.objectmethod.demo.entity.CountryBean;
@Repository
public interface CityBeanRepository extends JpaRepository<CityBean, Long>{
	public CityBean findByName(String name);
	public List<CityBean> findByCountryCode(String nation);
	public CityBean findById(int id);
	public List<CityBean> findByNameStartingWith(String name);
	public String removeById(int ident);
}
