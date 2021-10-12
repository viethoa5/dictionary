public class Word {
        private String word_target;
        private String word_expland;
        public Word() {
            this.word_target = "";
            this.word_expland = "";
        }
        public Word(String word_target, String word_expland) {
            this.word_target = word_target;
            this.word_expland = word_expland;
        }

        public String getWord_expland() {
            return word_expland;
        }

        public String getWord_target() {
            return word_target;
        }

        public void setWord_expland(String word_expland) {
               this.word_expland = word_expland;
           }

         public void setWord_target(String word_target) {
            this.word_target = word_target;
        }
}
