/* eslint-disable no-unused-vars */
<template>
  <div>
    <div class="header">
      <h2 class="label">Book Search</h2>
    </div>

    <div class="columns">
      <div class="column">
        <div class="has-text-centered">
          <img class="book" src="../views/assets/Book.gif" />
        </div>
      </div>
      <div class="column">
        <div class="form">
          <div class="form-input">
            <span>Enter Book ISBN:</span>
            <br />
            <div class="field has-addons">
              <input type="text" v-model="book.isbn" placeholder="10 or 13 digit ISBN" />

              <button
                class="button is-info"
                :disabled="!isValidForm"
                v-on:click="searchBooks(10, 13)"
              >Search</button>
            </div>
          </div>
          <br />
          <button class="button is-info is-small" v-on:click="manualBook = true; bookExist=false; clearSearchBT();">No ISBN</button>
        </div>
        <br />

        <div>
          <span v-if="showBook">
            <img v-bind:src="book.thumbnail" />
            Title: {{ book.title }}
            <p>Author: {{ book.author }}</p>
          </span>
        </div>
        <br />
        <div>
          <h1
            class="has-text-black is-size-6"
            v-if="showBook"
          >To add this book to your personal library, click below:</h1>
          <button v-if="showBook" v-on:click="addBookToLibrary">Add Book To Library</button>
          <p class="has-text-info" v-if="success">Book Added Successfully!</p>
          <p class="has-text-danger" v-if="bookExist">Book already in family library</p>
        </div>
      </div>
      <div class="column">
        <div class="form" v-if="manualBook">
          <div class="form-input">
            <p v-if="error1" class="has-text-danger">Your book could not be found.</p>
            <p class="has-text-danger">Please add your book below:</p>
            <span class="label">Title:</span>
            <input type="text" v-model="book.title" placeholder="Enter Title Here" />
            <span class="label">Author:</span>
            <input type="text" v-model="book.author" placeholder="Enter Author Here" />
          </div>
          <button v-on:click="addBookToLibraryManually">Add Book</button>
          <button v-on:click="clearSearch">Cancel</button>
          <p v-if="success2">Book Added Successfully!</p>
        </div>
        <i class="gg-trophy"></i>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import { eventBus } from "../main.js";

export default {
  props: {
    apiURL: String
  },
  data() {
    return {
      bookExist: false,
      showBook: false,
      manualBook: false,
      success: false,
      success2: false,
      error1: false,
      book: {
        isbn: "",
        title: "",
        author: "",
        thumbnail: ""
      }
    };
  },
  methods: {
    searchBooks(minlength, maxlength) {
      this.bookExist = false;
      this.manualBook = false;
      this.success = false;
      let mnlen = minlength;
      let mxlen = maxlength;

      if (this.book.isbn.length < mnlen || this.book.isbn.length > mxlen) {
        alert(
          "Please input the ISBN with digits only between " +
            mnlen +
            " and " +
            mxlen +
            " characters (Ex: 0141439556)"
        );
        return false;
      } else {
        axios
          .get(
            "https://openlibrary.org/api/books?format=json&jscmd=data&bibkeys=ISBN:" +
              this.book.isbn
          )

          .then(response => {
            //this.book.title = response.data['ISBN:1847246923'].title; THIS WORKS!!!
            let tempIsbn = this.book.isbn;
            if (response.data["ISBN:" + tempIsbn].cover.small == null) {
              this.book.thumbnail = "";
              this.book.title = response.data["ISBN:" + tempIsbn].title;
              this.book.author =
                response.data["ISBN:" + tempIsbn].authors[0].name;
            } else if (
              response.data["ISBN:" + tempIsbn].authors[0].name == null
            ) {
              this.book.title = response.data["ISBN:" + tempIsbn].title;
              this.book.author = "";
              this.book.thumbnail =
                response.data["ISBN:" + tempIsbn].cover.small;
            } else {
              this.book.title = response.data["ISBN:" + tempIsbn].title;
              this.book.author =
                response.data["ISBN:" + tempIsbn].authors[0].name;
              this.book.thumbnail =
                response.data["ISBN:" + tempIsbn].cover.small;
            }
            this.showBook = true;

            // this.book.isbn = ' ';
          })
          // eslint-disable-next-line no-unused-vars
          .catch(error => {
            this.manualBook = true;
            this.showBook = false;
            this.error1 = true;
          });
      }
    },

    addBookToLibraryManually() {
      axios
        .post(`${process.env.VUE_APP_REMOTE_API}/api/addBook`, this.book, {
          headers: {
            Authorization: "Bearer " + localStorage.getItem("Authorization")
          }
        })
        // eslint-disable-next-line no-unused-vars
        .then(response => {
          this.success = true;
          this.manualBook = false;
          this.bookExist = false;
          this.clearSearchBT();
          eventBus.$emit("refreshBooks");
        })
        .catch(error => {
          this.bookExist = true;
          this.showBook = false;
          this.clearSearchBT();
          console.log(error + " there was an error");
        });
    },

    addBookToLibrary() {
      axios
        .post(`${process.env.VUE_APP_REMOTE_API}/api/addBook`, this.book, {
          headers: {
            Authorization: "Bearer " + localStorage.getItem("Authorization")
          }
        })
        // eslint-disable-next-line no-unused-vars
        .then(response => {
          this.success = true;
          this.showBook = false;
          this.clearSearchBT();
          eventBus.$emit("refreshBooks");
        })
        .catch(error => {
          this.book.isbn = "";
          this.bookExist = true;
          this.showBook = false;
          this.clearSearchBT();
          console.log(error + " there was an error");
        });
    },

    clearSearch() {
      this.book.isbn = "";
      this.book.title = "";
      this.book.author = "";
      this.book.thumbnail = "";
      this.showBook = false;
      this.manualBook = false;
      this.success = false;
      this.success2 = false;
      
    },
    clearSearchBT() {
      this.book.isbn = "";
      this.book.title = "";
      this.book.author = "";
    },
    bookExistFlip(){
      this.bookExit = !this.bookExist
    }
  },
  computed: {
    isValidForm() {
      return this.book.isbn != "";
    }
  }
};
</script>

<style scoped>
h2 {
  background-color:mediumpurple;
}
.book {
  width: 35%;
  height: auto;
  display: block;

  margin-left: 30%;
  padding-top: 5%;
}
.form-input {
  padding-top: 3%;
} 
</style>
