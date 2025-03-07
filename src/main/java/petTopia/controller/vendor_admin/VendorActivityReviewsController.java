package petTopia.controller.vendor_admin;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import petTopia.model.vendor.VendorActivityReview;
import petTopia.repository.vendor_admin.VendorActivityReviewRepository;

@Controller
public class VendorActivityReviewsController {

	@Autowired
	private VendorActivityReviewRepository vendorActivityReviewRepository;

	@ResponseBody
	@GetMapping("/api/vendor_admin/activityreviews")
	public ResponseEntity<?> getReviewsByVendorActivityId(@RequestParam Integer vendorActivityId) {
		List<VendorActivityReview> vendorActivityReviews = vendorActivityReviewRepository
				.findByVendorActivityId(vendorActivityId);
		System.err.println(vendorActivityReviews);
		if (vendorActivityReviews.isEmpty()) {
			return ResponseEntity.ok(Collections.emptyList()); // ✅ 返回空数组 []
		}
		return ResponseEntity.ok(vendorActivityReviews);
	}

}
