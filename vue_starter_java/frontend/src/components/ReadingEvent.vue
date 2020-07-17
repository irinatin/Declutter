<template>
  <div>
    <h2 class="label">Record A Reading Event</h2>
    <br />
    <span class="field">Select Book: </span>
    <select id="books" v-on:click="showFamUsersBtn()" v-model="readingEvent.bookId">
      <option v-for="book in books" v-bind:key="book.id" :value="book.id">{{book.title}}</option>
    </select>
    <br />
    <br />
    <span v-if="showUsersButton" class="field">Select Family Member: </span>
    <select v-if="showUsersButton" id="users" v-model="readingEvent.userId">
      <option v-for="user in users" v-bind:key="user.id" :value="user.id">{{user.firstName}}</option>
    </select>
    <br />
    <br />

    <div class="form">
      <div class="form-input">
        <span class="field">Enter Reading Time: </span>
        <input type="number" placeholder="In Minutes" v-model="readingEvent.readingTime" />
      </div>
    </div>
    <br />

    <div class="form">
      <div class="form-input">
        <span class="field">Enter Reading Date: </span>
        <input type="text" placeholder="YYYY-MM-dd" v-model="readingEvent.readingDate" />
      </div>
    </div>
    <br />

    <span class="field">Select Reading Format: </span>
    <select id="format" v-model="readingEvent.format">
      <option v-for="format in formats" v-bind:key="format.id">{{format.format}}</option>
    </select>
    <br />
    <br />

    <label class="field" for="checkbox">Book Completed: </label>
    <input type="checkbox" id="checkbox" v-model="readingEvent.completed" />
    <br />

    <div>
      <button class="button is-small is-info" v-on:click="addReadingEvent">Submit</button>
    </div>
    <br />
  </div>
</template>


<script>
import axios from "axios";
import { eventBus } from "../main.js";

export default {
  components: {},

  props: {
    apiURL: String
  },
  data() {
    return {
      showUsersButton: false,
      showUsers: false,
      showForm: false,
      readingEvent: {
        userId: "",
        bookId: "",
        readingTime: 0,
        readingDate: "",
        format: "",
        completed: false
      },
      currentUser: {
        userId: "",
        userName: "",
        password: "",
        confirmPassword: "",
        passwordMatching: "",
        role: ""
      },
      books: [],
      users: [],
      formats: [
        {
          id: 1,
          format: "Paper"
        },
        {
          id: 2,
          format: "Digital/Kindle"
        },
        {
          id: 3,
          format: "Audiobook"
        },
        {
          id: 4,
          format: "Read-Aloud (Reader)"
        },
        {
          id: 5,
          format: "Read-Aloud (Listener)"
        },
        {
          id: 6,
          format: "Other"
        }
      ]
    };
  },

  created() {
    axios
      .get(`${process.env.VUE_APP_REMOTE_API}/api/getCurrentUser`, {
        headers: {
          Authorization: "Bearer " + localStorage.getItem("Authorization")
        }
      })
      .then(response => {
        this.currentUser = response.data;
        if (this.currentUser.role === "user") {
          this.showUsersButton = true;
        }
      })
      .catch(error => {
        console.log(error + " there was an error");
      });

    axios
      .get(`${process.env.VUE_APP_REMOTE_API}/api/getAllBooks`, {
        headers: {
          Authorization: "Bearer " + localStorage.getItem("Authorization")
        }
      })
      .then(response => {
        this.books = response.data;
      })
      .catch(error => {
        console.log(error + " there was an error");
      });
    axios
      .get(`${process.env.VUE_APP_REMOTE_API}/api/getUser`, {
        headers: {
          Authorization: "Bearer " + localStorage.getItem("Authorization")
        }
      })
      .then(response => {
        this.users = response.data;
      })
      .catch(error => {
        console.log(error + " there was an error");
      });
    eventBus.$on("refreshBooks", () => {
      axios
        .get(`${process.env.VUE_APP_REMOTE_API}/api/getAllBooks`, {
          headers: {
            Authorization: "Bearer " + localStorage.getItem("Authorization")
          }
        })
        .then(response => {
          this.books = response.data;
        })
        .catch(error => {
          console.log(error + " there was an error");
        });
    });
    eventBus.$on("refreshFamily", () => {
      axios
        .get(`${process.env.VUE_APP_REMOTE_API}/api/getUser`, {
          headers: {
            Authorization: "Bearer " + localStorage.getItem("Authorization")
          }
        })
        .then(response => {
          this.users = response.data;
        })
        .catch(error => {
          console.log(error + " there was an error");
        });
    });
  },

  methods: {
    showFamUsersBtn() {
      if (this.currentUser.role === "user") {
        this.showUsersButton = true;
      }
    },

    showFamUsers() {
      if (this.currentUser.role === "user") {
        this.showUsers = true;
      }
    },
    showFormButton() {
      this.showForm = true;
    },
    getBooks() {
      axios
        .get(`${process.env.VUE_APP_REMOTE_API}/api/getAllBooks`, {
          headers: {
            Authorization: "Bearer " + localStorage.getItem("Authorization")
          }
        })
        .then(response => {
          this.books = response.data;
        })
        .catch(error => {
          console.log(error + " there was an error");
        });
    },

    selectUser() {
      axios
        .get(`${process.env.VUE_APP_REMOTE_API}/api/getUser`, {
          headers: {
            Authorization: "Bearer " + localStorage.getItem("Authorization")
          }
        })
        .then(response => {
          this.users = response.data;
        })
        .catch(error => {
          console.log(error + " there was an error");
        });
    },

    addReadingEvent() {
      if (this.readingEvent.userId === "") {
        this.readingEvent.userId = this.currentUser.id;
      }

      if (document.querySelector("#checkbox").checked) {
        this.readingEvent.completed = true;
      }
      axios
        .post(
          `${process.env.VUE_APP_REMOTE_API}/api/addReadingEvent`,
          this.readingEvent,
          {
            headers: {
              Authorization: "Bearer " + localStorage.getItem("Authorization")
            }
          }
        )
        // eslint-disable-next-line no-unused-vars
        .then(response => {
          if (response.status == 200) {
            eventBus.$emit("refreshReadingEvent");
            alert("Your reading event has been saved, BookWorm!");
            this.readingEvent.userId = "";
            this.readingEvent.bookId = "";
            this.readingEvent.readingTime = 0;
            this.readingEvent.readingDate = "";
            this.readingEvent.format = "";
            this.readingEvent.completed = false;
          } else {
            alert("Your event did not save");
          }
        })
        .catch(error => {
          console.log(error + " there was an error");
        });
    },
    getCurrentUser() {
      axios
        .get(`${process.env.VUE_APP_REMOTE_API}/api/getCurrentUser`, {
          headers: {
            Authorization: "Bearer " + localStorage.getItem("Authorization")
          }
        })
        .then(response => {
          this.currentUser = response.data;
        })
        .catch(error => {
          console.log(error + " there was an error");
        });
    }
  },

  computed: {
    isValidForm() {
      return this.readingEvent > 0;
    },

    selectDate() {
      return this.date;
    }
  }
};
</script>


<style scoped>
h2 {
  background-color:#ff6666;
}

</style>
