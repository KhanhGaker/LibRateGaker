package com.example.librate_gaker

import android.app.Activity
import android.widget.Toast
import com.google.android.play.core.review.ReviewManagerFactory

object DirectAssessment {
    fun rateDirectAssessment(context: Activity) {
        // Tạo ReviewManager
        val reviewManager = ReviewManagerFactory.create(context)
        // Tạo request để lấy ReviewInfo
        val request = reviewManager.requestReviewFlow()
        // Khi yêu cầu được hoàn thành, hiển thị hộp thoại đánh giá
        request.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                // Lấy thông tin review
                val reviewInfo = task.result
                // Hiển thị hộp thoại đánh giá
                val flow = reviewManager.launchReviewFlow(context, reviewInfo)
                flow.addOnCompleteListener {
                    // Hộp thoại hoàn thành (không phải lúc nào người dùng cũng sẽ gửi đánh giá)
                }
            } else {
                // Xử lý lỗi nếu không thành công
                Toast.makeText(context, "" + task.getException().toString(), Toast.LENGTH_SHORT)
                    .show();
            }
        }
    }
}