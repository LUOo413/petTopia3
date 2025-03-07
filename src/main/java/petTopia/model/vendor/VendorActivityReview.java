package petTopia.model.vendor;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "vendor_activity_review")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class VendorActivityReview {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "vendor_id", nullable = false)
	@JsonIgnoreProperties({ "id" })
	private Vendor vendor;

//    @ManyToOne
//    @JoinColumn(name = "member_id", nullable = false)
//    private Member member;

	@Column(name = "review_time", nullable = false)
	private java.util.Date reviewTime;

	@Column(name = "review_content", nullable = false, length = 255)
	private String reviewContent;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "vendor_activity_id", nullable = false)
	private VendorActivity vendorActivity;

}
