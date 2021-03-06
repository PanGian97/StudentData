package pangian.car.studentdata.Lesson.RecView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jakewharton.rxbinding3.view.RxView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import pangian.car.studentdata.Lesson.Lesson;
import pangian.car.studentdata.R;

public class LessonsAdapter extends RecyclerView.Adapter<LessonsViewHolder> {

    private List<Lesson> lessons = new ArrayList<>();
    Lesson currentLesson;
    private final PublishSubject<Integer> onClickSubject = PublishSubject.create();
    @NonNull
    @Override
    public LessonsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.lesson_iten, parent, false);



        return new LessonsViewHolder(itemView);
    }

    public Observable<Integer> getItemClickSignal() {
        return onClickSubject;
    }


    @Override
    public void onBindViewHolder(@NonNull LessonsViewHolder holder, int position) {

         currentLesson = lessons.get(position);
        holder.idTxt.setText(String.valueOf(currentLesson.getId()));
        holder.titleTxt.setText(currentLesson.getName());


        RxView.clicks(holder.itemView)
                .map(aVoid -> getLessonIdAt(position))
                .subscribe(onClickSubject);//?
    }

    @Override
    public void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        onClickSubject.onComplete();
    }

    public void setLessons(List<Lesson> lessons){
        this.lessons = lessons;
        notifyDataSetChanged();
    }

    public int getLessonIdAt(int position)//now we can get the note of the adapter to the outside
    {
        return lessons.get(position).getId();
    }

    @Override
    public int getItemCount() {
        return lessons.size();
    }


}