package chung.bb;

import java.util.List;

/**
 * Created by carolynhung on 10/24/16.
 */
public class Story {
    public Story(){};

    private List<Story> storyList;

    private String story;
    private int id;

    public Story(String story){
        this.story = story;
    };
    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Story> getStoryList() {
        return storyList;
    }

    public void setStoryList(List<Story> storyList) {
        this.storyList = storyList;
    }

}
