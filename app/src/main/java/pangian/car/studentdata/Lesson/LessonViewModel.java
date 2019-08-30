package pangian.car.studentdata.Lesson;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class LessonViewModel extends AndroidViewModel {

    LessonRepository lessonRepository;

    public LessonViewModel(@NonNull Application application) {
        super(application);

        lessonRepository = new LessonRepository(application);
    }


    public void addLesson(int id, String name) {
        lessonRepository.checkIfLessonExists(new Lesson(id, name));
    }

    public LiveData<List<Lesson>> getAllLessons() {
        return lessonRepository.getAllLessons();
    }

    public LiveData<String> messageHandler() {
        return lessonRepository.messageHandler();
    }


}
