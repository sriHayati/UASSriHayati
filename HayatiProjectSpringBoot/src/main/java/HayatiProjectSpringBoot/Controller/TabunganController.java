package HayatiProjectSpringBoot.Controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import HayatiProjectSpringBoot.Dao.TabunganDao;
import HayatiProjectSpringBoot.Model.TabunganModel;

@RestController
@RequestMapping("/bank")
public class TabunganController {
	@Autowired
	TabunganDao TabDao;
	
	@PostMapping("/tabunganPost")
	public TabunganModel saveTabungan(@Valid @RequestBody TabunganModel tm) {
		return TabDao.save(tm);
	}
	
	@GetMapping("/tabunganGetAll")
	public List<TabunganModel> getSemuaTabungan(){
		return TabDao.findAll();
	}
	
	@GetMapping("/tabunganGetById/{id}")
	public ResponseEntity<TabunganModel> getTabunganById(@PathVariable(value="id") Long id){
		TabunganModel tm=TabDao.getFindOne(id);
		if(tm==null) {
			return ResponseEntity.notFound().build();
		}else {
			return ResponseEntity.ok().body(tm);
		}
	}
	
	@GetMapping("/tabunganGetByNik/{nik}")
	public List<TabunganModel> getTabunganByNik(@PathVariable(value="nik") String nik){
		return TabDao.getFindByNik(nik);
	}
	
	@DeleteMapping("tabunganDelete/{id}")
	public ResponseEntity<TabunganModel> deleteTabungan(@PathVariable(value="id")Long id){
		TabunganModel b=TabDao.getFindOne(id);
		if(b==null) {
			return ResponseEntity.notFound().build();
		}else {
			TabDao.hapusTabungan(id);
			return  ResponseEntity.ok().build();
		}
	}
	@PutMapping("tabunganUpdate/{id}")
	public ResponseEntity<TabunganModel> updateSaldo (@PathVariable(value="id") Long id,@Valid @RequestBody TabunganModel tabungan){
		TabunganModel b=TabDao.getFindOne(id);
		if(b==null) {
			return ResponseEntity.notFound().build();
		}else {
			b.setSaldo(b.getSaldo()-b.getKredit()+b.getDebet());
			b.setKredit(tabungan.getKredit());
			b.setDebet(tabungan.getDebet());
		
			
			TabunganModel bResult=TabDao.update(b);
			return ResponseEntity.ok().body(bResult);
		}

	}
}
