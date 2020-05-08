package ua.lviv.lgs.periodicals.services;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import ua.lviv.lgs.periodicals.dtos.PeriodicalCreateFormDto;
import ua.lviv.lgs.periodicals.entities.Periodical;
import ua.lviv.lgs.periodicals.entities.PeriodicalPhoto;
import ua.lviv.lgs.periodicals.repositories.PeriodicalPhotoRepository;
import ua.lviv.lgs.periodicals.repositories.PeriodicalRepository;

@Service
public class PeriodicalService {

  private final PeriodicalRepository periodicalRepository;
  private final PeriodicalPhotoRepository periodicalPhotoRepository;

  @Autowired
  public PeriodicalService(PeriodicalRepository periodicalRepository,
                           PeriodicalPhotoRepository periodicalPhotoRepository) {
    this.periodicalRepository = periodicalRepository;
    this.periodicalPhotoRepository = periodicalPhotoRepository;
  }

  public void save(PeriodicalCreateFormDto periodicalCreateFormDto, MultipartFile[] photos) {
    Periodical periodical = new Periodical(
      periodicalCreateFormDto.getName(),
      periodicalCreateFormDto.getDescription(),
      periodicalCreateFormDto.isActive()
    );

    periodical.setCreateAt(LocalDateTime.now());

    periodicalRepository.save(periodical);

    List<PeriodicalPhoto> periodicalPhotos = Arrays.stream(photos)
      .map(this::mapToBytes)
      .filter(bytes -> bytes.length > 0)
      .map(bytes -> Base64.getEncoder().encodeToString(bytes))
      .map(encodedString -> new PeriodicalPhoto(encodedString, periodical))
      .collect(Collectors.toList());

    periodicalPhotoRepository.saveAll(periodicalPhotos);
  }

  public List<Periodical> findAll() {
    return periodicalRepository.findByActiveTrue();
  }

  private byte[] mapToBytes(MultipartFile multipartFile) {
    try {
      return multipartFile.getBytes();
    } catch (IOException e) {
      System.out.println("Can't save file with name " + multipartFile.getName());
      return new byte[]{};
    }
  }
}
