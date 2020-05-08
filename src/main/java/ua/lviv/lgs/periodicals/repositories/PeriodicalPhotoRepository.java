package ua.lviv.lgs.periodicals.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ua.lviv.lgs.periodicals.entities.PeriodicalPhoto;

@Repository
public interface PeriodicalPhotoRepository extends JpaRepository<PeriodicalPhoto, Integer> {
}
