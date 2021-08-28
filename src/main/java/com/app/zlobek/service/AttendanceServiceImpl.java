package com.app.zlobek.service;

import com.app.zlobek.dao.AttendanceRepository;
import com.app.zlobek.entity.Attendance;
import com.app.zlobek.entity.Parent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AttendanceServiceImpl implements AttendanceService {

    AttendanceRepository attendanceRepository;

    @Autowired
    public AttendanceServiceImpl(AttendanceRepository attendanceRepository) {
        this.attendanceRepository = attendanceRepository;
    }

    @Override
    public List<Attendance> findAll() {
        return attendanceRepository.findAll();

    }

    @Override
    public List<Attendance> findAllByIdAndDate(){
        //tutaj zamiast liczby na sztywno wartość która będzie przekazywana po zalogowaniu - zrobić, żeby ustalać id po logowaniu
        int parentId = 1;

        int tempAttendanceListSize = attendanceRepository.findAllByParentAndAttendanceDateBetween
                                                    (new Parent(parentId), hourGuard(),hourGuard().plusDays(10))
                                                    .size();

        if(tempAttendanceListSize<10){
            for(int i = tempAttendanceListSize; i<10;i++){
                Attendance attendance = new Attendance(new Parent(parentId), hourGuard().plusDays(i), true);
                attendanceRepository.save(attendance);
            }
        }

        return attendanceRepository.findAllByParentAndAttendanceDateBetween
                                    (new Parent(parentId), hourGuard(),hourGuard().plusDays(10));
    }

    @Override
    public Attendance findById(int id) {
        Optional<Attendance> result = attendanceRepository.findById(id);

        Attendance tempAttendaceStatus = null;

        if (result.isPresent()) {
            tempAttendaceStatus = result.get();
        } else {
            throw new RuntimeException("Nie znalazłem statusu");
        }
        return tempAttendaceStatus;
    }

    @Override
    public void save(Attendance attendance) {
        attendanceRepository.save(attendance);
    }

    @Override
    public void deleteById(int id) {
        attendanceRepository.deleteById(id);
    }

    private LocalDate hourGuard(){

        LocalTime hourGuard = LocalTime.now();

        if(hourGuard.isBefore(LocalTime.of(7,0,0))){
            return LocalDate.now();
        } else {
            return LocalDate.now().plusDays(1);
        }

    }
}
