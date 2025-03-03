package petTopia.controller.vendor_admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import petTopia.model.vendor_admin.ActivityPeopleNumber;
import petTopia.model.vendor_admin.ReviewPhoto;
import petTopia.model.vendor_admin.Vendor;
import petTopia.model.vendor_admin.VendorActivity;
import petTopia.model.vendor_admin.VendorActivityImages;
import petTopia.model.vendor_admin.VendorReviews;
import petTopia.repository.vendor_admin.VendorReviewsRepository;
import petTopia.service.vendor_admin.VendorReviewsService;

@Controller

public class VendorReviewsController {

	@Autowired
	private VendorReviewsService vendorReviewsService;

	@Autowired
	private VendorReviewsRepository vendorReviewsRepository;

	@GetMapping("/vendor_admin/reviews")
	public String getReviewsPage() {
		return "vendor_admin/vendor_admin_reviews";
	}

	@ResponseBody
	@GetMapping("/api/vendor_admin/reviews/{vendorId}")
	public ResponseEntity<List<VendorReviews>> getVendorReviewsByVendorId(@PathVariable Integer vendorId) {
		List<VendorReviews> reviews = vendorReviewsService.getReviewsByVendorId(vendorId);

		if (!reviews.isEmpty()) {
			return ResponseEntity.ok(reviews);
		}

		return ResponseEntity.status(404).body(null);
	}

	// 取得店家的所有評論
	@GetMapping("/api/vendor_admin/review/{vendorId}")
	public List<VendorReviews> getReviewsByVendorId(@PathVariable Integer vendorId) {
		return vendorReviewsService.getReviewsByVendorId(vendorId);
	}

	// 取得評論的所有照片
	@GetMapping("/api/vendor_admin/review/photos/{reviewId}")
	public List<ReviewPhoto> getPhotosByReviewId(@PathVariable Integer reviewId) {
		return vendorReviewsService.getPhotosByReviewId(reviewId);
	}

//	// 新增評論
//	@ResponseBody
//	@PostMapping("/api/vendor_admin/review/add")
//	public ResponseEntity<?> addReview(@RequestBody VendorReviews review) {
//		try {
//			// 假设saveReview是保存评论的方法
//			VendorReviews savedReview = vendorReviewsRepository.save(review);
//			return new ResponseEntity<>(savedReview, HttpStatus.CREATED); // 返回保存的评论数据
//		} catch (Exception e) {
//			e.printStackTrace();
//			return new ResponseEntity<>("Failed to add review", HttpStatus.BAD_REQUEST); // 提供错误信息
//		}
//	}
//
//	// 新增評論照片
//	@PostMapping("/api/vendor_admin/review/add/photo")
//	public ReviewPhoto addReviewPhoto(@RequestBody ReviewPhoto photo) {
//		return vendorReviewsService.addReviewPhoto(photo);
//	}

	// 刪除評論
	@ResponseBody
	@DeleteMapping("/api/vendor_admin/review/delete/{reviewId}")
	public ResponseEntity<?> deleteReview(@PathVariable Integer reviewId) {
		Optional<VendorReviews> review = vendorReviewsRepository.findById(reviewId);
		Map<String, String> response = new HashMap<>();
		if (review.isPresent()) {
			boolean deleted = vendorReviewsService.deleteReview(reviewId);

			response.put("message", "刪除成功");
		} else {
			response.put("message", "刪除失敗無此資料");
		}

		return ResponseEntity.ok(response);
	}

}
