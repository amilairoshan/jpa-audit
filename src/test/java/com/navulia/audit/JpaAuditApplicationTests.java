package com.navulia.audit;

import com.navulia.audit.entity.Supplier;
import com.navulia.audit.exception.ResourceNotFoundException;
import com.navulia.audit.repository.SupplierRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class JpaAuditApplicationTests {
	@Autowired
	private SupplierRepository supplierRepository;
	@Mock
	private SecurityContext securityContext;
	@Mock
	private Authentication authentication;

	@BeforeEach
	public void init(){
		when(securityContext.getAuthentication()).thenReturn(authentication);
		SecurityContextHolder.setContext(securityContext);
		when(authentication.isAuthenticated()).thenReturn(true);
	}
	@Test
	public void testCreatedByAuditing(){
		//mock authenticated username
		when(authentication.getName()).thenReturn("admin");
		//create a supplier
		Supplier supplier = Supplier.builder().name("Saman").contactDetails("0778956345").address("No:02,Kaluthara RD,Colombo 10").specialties("Dress").build();
		Supplier actualResult=supplierRepository.save(supplier);
		//verify that createdBy and createdDate
		assertEquals(actualResult.getCreatedBy(),"admin");
		assertNotNull(actualResult.getCreatedDate());
	}

	@Test
	public void testModifiedByAuditing(){
		//mock authenticated username
		when(authentication.getName()).thenReturn("admin");
		//create a supplier
		Supplier supplier = Supplier.builder().name("Amal").contactDetails("0778345534").address("No:02,Galle RD,Colombo 10").specialties("Meat").build();
		supplierRepository.save(supplier);
		when(authentication.getName()).thenReturn("supplier");
		//update the authenticate username
		Supplier updatedSupplier= Supplier.builder().id(supplier.getId()).name("Amal_test").contactDetails("0778345534").address("No:03,Galle RD,Colombo 10").specialties("Meat").build();
		Supplier actualResult=supplierRepository.save(updatedSupplier);
		//verify that createdBy and createdDate
		assertEquals(actualResult.getLastModifiedBy(),"supplier");
		assertNotNull(actualResult.getLastModifiedDate());
	}
}
