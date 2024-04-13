package com.thaisbg.campaignpoints.points;

import com.thaisbg.campaignpoints.points.model.Score;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("points")
@RequiredArgsConstructor
public class PointsController {

    private final PointsRepository pointsRepository;

    @GetMapping
    public List<Score> getAllScores() {
        return pointsRepository.getAllScores();
    }

}
