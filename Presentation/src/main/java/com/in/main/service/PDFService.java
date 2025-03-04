package com.in.main.service;

import com.in.main.dto.RatingDto;

public interface PDFService {
	byte[] generateRatingPDF(RatingDto rating);
}
