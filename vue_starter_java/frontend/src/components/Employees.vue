<template>
    <div class="employees">
        <h2>{{title}}</h2>
        <ul>
            <li>
                <img class="photo" v-bind:src="data.photo" alt="employee photo"/>
            </li>
            <li v-for="isbn in searchBooks(data.adult)" v-bind:isbn="book.isbn">
           
                
                {{this.book.thumbnail }}
                {{this.book.title}}
                {{this.book.author}}

                
            </li>
            <!-- <li v-for="isbn in data.adult" v-bind:isbn="book.isbn" > -->
               
             
            
        </ul>
    </div>
</template>

<script>
import axios from "axios";

export default {
    
    name: 'employees',
    props: {
        title: String,
        data: Object
    },
    data() {
        return {
  
    book: {
        isbn: "",
        title: "",
        author: "",
        thumbnail: ""
      }
        }
    },
    methods: {
       searchBooks(isbn) {
        axios
          .get(
            "https://openlibrary.org/api/books?format=json&jscmd=data&bibkeys=ISBN:" +
              isbn
          )

          .then(response => {
            let tempIsbn = isbn;
            this.book.title = response.data["ISBN:" + tempIsbn].title;
            this.book.author =
              response.data["ISBN:" + tempIsbn].authors[0].name;
            this.book.thumbnail = response.data["ISBN:" + tempIsbn].cover.small;
            
            
          })
          .catch(error => {
            console.log(error + " there was an error");
          });
    },
       }  
}


</script>

<style>

.photo {
    width: 30%;
    height: auto;
}
.shopping-list {
    width:450px;
    background: #fff;
    margin: 50px auto;
    font-family: 'Roboto Condensed', sans-serif;
    border-radius: 10px;
}
h2{
    background:#f2f2f2;
    color:#5c8578;
    padding:10px;
    font-size:24px;
    text-transform: uppercase;
    text-align: center;
    margin: 0px;
    border-top-left-radius: 10px;
    border-top-right-radius: 10px;
}
ul {
    list-style-type: none;
    margin:0px;
    padding:0px;
}
li {
    font-size: 24px;
    border-bottom:1px solid #f2f2f2;
    padding:10px 20px;
    cursor: pointer;
}
li:last-child{
    border:0px;
}
li.completed {
    text-decoration: line-through;
    color:darkgrey;
}
i.far.fa-check-circle { 
    float:right;
    color: darkgray;
}

i.far.fa-check-circle.completed {
    color:green;
}
input[type="checkbox"] {
    font-size:40px;
    vertical-align: middle;
    margin-top:0px;
}
</style>
