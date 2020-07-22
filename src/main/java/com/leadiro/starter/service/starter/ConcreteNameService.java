package com.leadiro.starter.service.starter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.leadiro.starter.service.NameService;
import com.leadiro.starter.service.starter.dto.Name;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ConcreteNameService implements NameService{

	@Override
	public List<Name> nameClean(List<Name> name) {
		List<Name> listNames = new ArrayList<Name>();
		try {
			for (Name name2 : name) {
				Name n = new Name();
				System.out.println(name2.getName());
				
				String arrname[] = name2.getName().split(" ");
				
				if(arrname.length==2) {
					n.setFirstName(arrname[0]);
					n.setLastName(arrname[1]);
				} else if(arrname.length==3) {
					n.setFirstName(arrname[0]);
					n.setLastName(arrname[2]);
				} else if(arrname.length==4) {
					n.setFirstName(arrname[0]);
					n.setLastName(arrname[2]);
				} else {
					n.setFirstName(arrname[0]);
				}
				listNames.add(n);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listNames;
	}

}
