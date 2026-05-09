package com.macabi.controlpanel.service;
import com.macabi.controlpanel.dto.booking.BookingMealRequestDto;
import com.macabi.controlpanel.dto.booking.BookingMealResponseDto;
import com.macabi.controlpanel.dto.booking.BookingMealUpdateDto;
import com.macabi.controlpanel.exception.ResourceNotFoundException;
import com.macabi.controlpanel.mapper.BookingMealMapper;
import com.macabi.controlpanel.model.BookingMeal;
import com.macabi.controlpanel.model.Meal;
import com.macabi.controlpanel.model.Participant;
import com.macabi.controlpanel.repository.BookingMealRepository;
import com.macabi.controlpanel.repository.MealRepository;
import com.macabi.controlpanel.repository.ParticipantRepository;
import com.macabi.controlpanel.service.iservice.BookingMealService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class BookingMealServiceImpl implements BookingMealService {
    
    private static final String         BOOKING_MEAL_RESOURCE = "BookingMeal";
    private static final String         PARTICIPANT_RESOURCE  = "Participant";
    private static final String         MEAL_RESOURCE         = "Meal";
    private static final String         ID_FIELD              = "id";
    private final BookingMealRepository bookingMealRepository;
    private final ParticipantRepository participantRepository;
    private final MealRepository        mealRepository;
    private final BookingMealMapper     bookingMealMapper;


    @Override
    @Transactional(readOnly = true)
    public List<BookingMealResponseDto> getAllBookingMeals() {
        return bookingMealRepository.findAll().stream()
                .map(bookingMealMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public BookingMealResponseDto getBookingMealById(Long id) {
        var bookingMeal = findBookingMealById(id);
        return bookingMealMapper.toResponseDto(bookingMeal);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookingMealResponseDto> getBookingMealsByParticipantId(Long participantId) {
        validateParticipantExists(participantId);
        return bookingMealRepository.findByParticipantId(participantId).stream()
                .map(bookingMealMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookingMealResponseDto> getActiveBookingMealsByParticipantId(Long participantId) {
        validateParticipantExists(participantId);
        return bookingMealRepository.findByParticipantIdAndActive(participantId, true).stream()
                .map(bookingMealMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookingMealResponseDto> getBookingMealsByMealId(Long mealId) {
        validateMealExists(mealId);
        return bookingMealRepository.findByMealId(mealId).stream()
                .map(bookingMealMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookingMealResponseDto> getActiveBookingMealsByProjectId(Long projectId) {
        return bookingMealRepository.findByProjectIdAndActive(projectId).stream()
                .map(bookingMealMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public BookingMealResponseDto createBookingMeal(BookingMealRequestDto bookingMealDto) {
        var participant      = findParticipantById(bookingMealDto.getParticipantId());
        var meal             = findMealById(bookingMealDto.getMealId());
        var bookingMeal      = bookingMealMapper.toEntity(bookingMealDto, participant, meal);
        var savedBookingMeal = bookingMealRepository.save(bookingMeal);
        return bookingMealMapper.toResponseDto(savedBookingMeal);
    }

    @Override
    public BookingMealResponseDto updateBookingMeal(Long id, BookingMealUpdateDto bookingMealDto) {
        var bookingMeal = findBookingMealById(id);
        Meal meal = null;
        if (bookingMealDto.getMealId() != null) {
            meal = findMealById(bookingMealDto.getMealId());
        }
        bookingMealMapper.updateEntityFromDto(bookingMealDto, bookingMeal, meal);
        var updatedBookingMeal = bookingMealRepository.save(bookingMeal);
        return bookingMealMapper.toResponseDto(updatedBookingMeal);
    }

    @Override
    public void deleteBookingMeal(Long id) {
        var bookingMeal = findBookingMealById(id);
        bookingMealRepository.delete(bookingMeal);
    }
    
    // Metodos privados 
    
    private BookingMeal findBookingMealById(Long id) {
        return bookingMealRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        BOOKING_MEAL_RESOURCE, ID_FIELD, id));
    }
    
    private Participant findParticipantById(Long id) {
        return participantRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        PARTICIPANT_RESOURCE, ID_FIELD, id));
    }
    
    private Meal findMealById(Long id) {
        return mealRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        MEAL_RESOURCE, ID_FIELD, id));
    }
    
    private void validateParticipantExists(Long participantId) {
        if (!participantRepository.existsById(participantId)) {
            throw new ResourceNotFoundException(PARTICIPANT_RESOURCE, ID_FIELD, participantId);
        }
    }
    
    private void validateMealExists(Long mealId) {
        if (!mealRepository.existsById(mealId)) {
            throw new ResourceNotFoundException(MEAL_RESOURCE, ID_FIELD, mealId);
        }
    }
}
