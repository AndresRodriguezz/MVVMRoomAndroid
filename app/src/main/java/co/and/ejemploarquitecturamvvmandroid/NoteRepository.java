package co.and.ejemploarquitecturamvvmandroid;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;

import androidx.lifecycle.LiveData;

public class NoteRepository {
    private NoteDao noteDao;
    private LiveData<List<Note>> allnotes;

    public NoteRepository(Application application){
        NoteDataBase dataBase = NoteDataBase.getInstance(application);
        noteDao = dataBase.noteDao();
        allnotes = noteDao.getAllNotes();
    }

    public void insert (Note note){
        new InsertNoteAsynTask(noteDao).execute(note);

    }

    public void update (Note note){
        new UpdateNoteAsyncTask(noteDao).execute(note);

    }

    public void delete (Note note){
        new DeleteNoteAsynTask(noteDao).execute(note);

    }

    public void deleteAllNotes(){
        new DeleteAllNotesAsynTask(noteDao).execute();

    }

    public LiveData<List<Note>> getAllnotes(){
        return allnotes;
    }

    private static class InsertNoteAsynTask extends AsyncTask<Note,Void,Void>{
        private NoteDao noteDao;

        private InsertNoteAsynTask(NoteDao noteDao){
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.insert(notes[0]);
            return null;
        }
    }

    private static class UpdateNoteAsyncTask extends AsyncTask<Note, Void, Void> {
        private NoteDao noteDao;
        private UpdateNoteAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }
        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.update(notes[0]);
            return null;
        }
    }

    private static class DeleteNoteAsynTask extends AsyncTask<Note,Void,Void>{
        private NoteDao noteDao;

        private DeleteNoteAsynTask(NoteDao noteDao){
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.delete(notes[0]);
            return null;
        }
    }

    private static class DeleteAllNotesAsynTask extends AsyncTask<Void,Void,Void>{
        private NoteDao noteDao;

        private DeleteAllNotesAsynTask(NoteDao noteDao){
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.deleteAllNotes();
            return null;
        }
    }
}
