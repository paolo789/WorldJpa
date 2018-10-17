package it.objectmethod.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import it.objectmethod.demo.entity.CityBean;
import it.objectmethod.demo.entity.CountryBean;
import it.objectmethod.demo.repository.CityBeanRepository;
import it.objectmethod.demo.repository.CountryRepository;

@Controller
public class DemoController {

	
	
	@Autowired
	private CountryRepository countryRepo;
	
	@RequestMapping("/seconda")
	public String country(ModelMap map) {
		List<String> cb=countryRepo.findAllContinents();
		map.addAttribute("cb",cb);
		return "hello";
	}
	
	
	
	@RequestMapping("/listanazioni")
	public String nations(HttpServletRequest request,ModelMap map, @RequestParam("Cont") String continent) {
		HttpSession session= request.getSession();
		List<CountryBean> n=countryRepo.findByContinent(continent);
		map.addAttribute("nazioni", n);
		session.setAttribute("cont", continent);
		return "ListaNazioni";
	}
	@Autowired
	private CityBeanRepository cbr;
	
	@RequestMapping("/listacitta")
	public String cities(ModelMap map,@RequestParam("nation") String nation) {
		List<CityBean> v=cbr.findByCountryCode(nation);
		map.addAttribute("citta", v);
		map.addAttribute("nation",nation);
		return "ListaCitta";
	}
	
	@RequestMapping("/aggiungimodifica")
	public String aggiungiModificaCitta(HttpServletRequest request,ModelMap map) {
		//INationDao iNationDao=new NationDaoImpl();
		List<CountryBean> countriesList=countryRepo.findAll();
		CityBean citta = new CityBean();
		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("identd"));
		} catch (NumberFormatException e) {
			id = 0;
		}
		if (id != 0) {
			//ICityDao iCityDao = new CityDaoImpl();
			citta = cbr.findById(id);
			map.addAttribute("citta",citta );
		}
		map.addAttribute("countries", countriesList);
		return  "CercaCittaModifica";
	}
	
	@RequestMapping("/ricercacitta")
	public String cercaCitta(ModelMap map, @RequestParam("cercacitta") String cercaCitta) {;
		List<CityBean> v=cbr.findByNameStartingWith(cercaCitta);
		map.addAttribute("listacitta", v);
		return "CercaCitta";
	}
	
	@RequestMapping("/cancella")	
	public String cancellaCitta(ModelMap map, @RequestParam("identd") String id, @RequestParam("countrycode") String countryCode) {
		int ident= Integer.parseInt(id);
		//ICityDao cd = new CityDaoImpl();
		 CityBean city=cbr.findById(ident);
		 cbr.delete(city);
		System.out.println("Sto cancellando "+countryCode);
		return "forward:listacitta?nation="+countryCode;
	}
	
	@RequestMapping("/modifica")
	public String modifica(HttpServletRequest request,ModelMap map, @RequestParam("nomecitta") String citta,@RequestParam("country") String countryCode,@RequestParam("district") String district,@RequestParam("population") String population) {
		CityBean cb = new CityBean();
		cb.setName(citta);
		cb.setCountryCode(countryCode);
		cb.setDistrict(district);
		cb.setPopulation(Integer.parseInt(population));
		cbr.save(cb);
		/*if (request.getParameter("id")!=null) {
			int id= Integer.parseInt(request.getParameter("id"));
			cb.setId(id);
			cd.modificaCitta(cb);
			map.addAttribute("nationCode", countryCode); //TODO meglio agire così
		}
		else {
			cd.insertCity(cb);
		}*/
		return "forward:listacitta?nation="+countryCode; //TODO piuttosto che così
	}
}
