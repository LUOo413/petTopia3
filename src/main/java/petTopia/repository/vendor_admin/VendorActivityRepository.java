package petTopia.repository.vendor_admin;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import petTopia.model.vendor_admin.VendorActivity;
import petTopia.model.vendor_admin.VendorActivityImages;

public interface VendorActivityRepository extends JpaRepository<VendorActivity, Integer> {
	// 你可以根據需要增加查詢方法，例如：
	// List<VendorActivity> findByVendorId(Integer vendorId);

	List<VendorActivity> findByVendorId(Integer vendorId);

	
}
