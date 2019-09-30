package HayatiProjectSpringBoot.Dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import HayatiProjectSpringBoot.Model.TabunganModel;
import HayatiProjectSpringBoot.Repository.TabunganRepository;

@Service
public class TabunganDao {
	@Autowired
	TabunganRepository TabRepository;
	
	// simpan
	public TabunganModel save(TabunganModel tm){
		TabunganModel t=tm;
		TabunganModel tabmo=TabRepository.findSaldoTabungan(tm.getNik());
		if(tabmo!=null) {
			t.setSaldo(tabmo.getSaldo()+t.getKredit()-t.getDebet());
			return TabRepository.save(t);
			
		}else {
			t.setSaldo(0+t.getKredit()-t.getDebet());
			return TabRepository.save(t);
		}
		
	}
	
	// tampil semua
	public List<TabunganModel> findAll(){
		return TabRepository.findAll();
	}
	
	// tampil by id
	public TabunganModel getFindOne(Long id) {
		return TabRepository.findOne(id);
	}
	
	//tampil by nik
	public List<TabunganModel> getFindByNik(String nik){
		return TabRepository.findTabunganByNik(nik);
	}
	
	
	public TabunganModel getfindSaldo(String nik) {
		return TabRepository.findSaldoTabungan(nik);
	}
	
	//delete
	public void hapusTabungan(Long id) {
		TabunganModel tabu=TabRepository.findOne(id);
		List<TabunganModel> dList=TabRepository.findTabunganByNik(tabu.getNik());
		for(TabunganModel dt : dList) {
			if(dt.getId() > id) {
				TabunganModel tam1=TabRepository.findOne(dt.getId());
				tam1.setSaldo(tam1.getSaldo()-tabu.getKredit()+tabu.getDebet());
				TabRepository.save(tam1);
			}
		}
		TabRepository.delete(id);
	}
	
	//update
	public TabunganModel update(TabunganModel tm) {
		TabunganModel tabmod=tm;
		TabunganModel tamSaldo=TabRepository.findOne(tabmod.getId());
		tamSaldo.setSaldo(tamSaldo.getSaldo()+tabmod.getKredit()-tabmod.getDebet());
		tamSaldo.setDebet(tabmod.getDebet());
		tamSaldo.setKredit(tabmod.getKredit());
		int tam=tamSaldo.getSaldo();
		List<TabunganModel> dataL=TabRepository.findTabunganByNik(tabmod.getNik());
		for(TabunganModel dt : dataL) {
			if(dt.getId() > tabmod.getId()) {
				TabunganModel tam1=TabRepository.findOne(dt.getId());
				tam1.setSaldo(tam+tam1.getKredit()-tam1.getDebet());
				TabRepository.save(tam1);
				tam=tam1.getSaldo();
			}
		}
		return TabRepository.save(tamSaldo);
	}
}
