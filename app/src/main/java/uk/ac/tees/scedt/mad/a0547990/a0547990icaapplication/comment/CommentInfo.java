package uk.ac.tees.scedt.mad.a0547990.a0547990icaapplication.comment;

public class CommentInfo {

        String name; //评论者
        String content; //评论内容

        public CommentInfo(){

        }

        public CommentInfo(String name, String content){
            this.name = name;
            this.content = content;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    public String toSpring(){
        return "Comment{name="+name+",content= "+content+"}";
    }
    }

