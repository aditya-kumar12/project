package main

import (
	"fmt"
	"net/http"
	"github.com/gorilla/mux"

	"io/ioutil"
	"encoding/json"
        "context"
        "log"
         "go.mongodb.org/mongo-driver/bson"
         "go.mongodb.org/mongo-driver/mongo"

        "go.mongodb.org/mongo-driver/mongo/options"

        
)

type payment struct {
	Id       string `json:"Id"`
	Uid      string `json:"Uid"`
	Mid      string `json:"MId"`
	Tid      string `json:"Tid"`
	Datetime string `json:"Datetime"`
        Amt      string `json:"Amt"`
	Remarks  string `json:"Remarks"`
}


var Allpayment []payment


func createpersonmet(w http.ResponseWriter,req *http.Request)  {

	fmt.Fprintf(w,"in create function")
	reqbody,_:=ioutil.ReadAll(req.Body)
	fmt.Printf("reqbody:",reqbody)

	var ppayment payment
	json.Unmarshal(reqbody,&ppayment)
        Allpayment=append(Allpayment,ppayment)
	fmt.Println("Received object ",ppayment.Id)

      
      

        // connect to mongodb
  clientOptions := options.Client().ApplyURI("mongodb://127.0.0.1:27017")
  client, err := mongo.Connect(context.TODO(), clientOptions)

   if err != nil {
        log.Fatal(err)
   }
  err = client.Ping(context.TODO(), nil)
  fmt.Fprintf(w, "Created")
   collection := client.Database("mydbb").Collection("payment")
 //MyPerson := Person{"1", "mongoname1", "City1","Address1"}
   insertResult, err := collection.InsertOne(context.TODO(), ppayment)
  if err != nil {
        log.Fatal(err)
    }
fmt.Println("Inserted a Single Document: ", insertResult.InsertedID)



}



func Allperson(w http.ResponseWriter,req *http.Request){


       clientOptions := options.Client().ApplyURI("mongodb://127.0.0.1:27017")
    client, err := mongo.Connect(context.TODO(), clientOptions)

    if err != nil {
        log.Fatal(err)
    }

    err = client.Ping(context.TODO(), nil)

    if err != nil {
        log.Fatal(err)
    }

    fmt.Println("Connected to MongoDB!")

    collection := client.Database("mydbb").Collection("payment")

    findOptions := options.Find()

    var results []payment

  
   
  cur, err := collection.Find(context.TODO(), bson.D{}, findOptions)    //bson.D{}
  if err != nil {
      log.Fatal(err)
  }

  for cur.Next(context.TODO()) {
      var elem payment
      err := cur.Decode(&elem)
      if err != nil {
          log.Fatal(err)
      }

fmt.Printf("Found:", elem)
      results = append(results, elem)
  }

  if err := cur.Err(); err != nil {
      log.Fatal(err)
  }

  cur.Close(context.TODO())
    fmt.Printf("Found multiple documents (array of pointers): %+v\n", results)

	fmt.Println("sending all person in response")
	//json.NewEncoder(w).Encode(Allpeople)
        json.NewEncoder(w).Encode(results)



}




func Getperson(w http.ResponseWriter,req *http.Request){


 vars:=mux.Vars(req)
 idval:=vars["id"]   //this is the id of path parameter that we gave 
 

  for _,person:=range Allpayment{
      if person.Id==idval{
       json.NewEncoder(w).Encode(person)    //only this particular person is sent there 
    }

  }


}
type update struct{
	Id       string `json:"Id"`
	Amt      string `json:"Amt"`
}


func Update(w http.ResponseWriter,req *http.Request)  {
   
clientOptions := options.Client().ApplyURI("mongodb://127.0.0.1:27017")
    client, err := mongo.Connect(context.TODO(), clientOptions)


   fmt.Fprintf(w,"in update function")
   
  fmt.Println("Connected to MongoDB!")

collection := client.Database("mydbb").Collection("payment")

    reqbody, _ := ioutil.ReadAll(req.Body)
	var request update
	json.Unmarshal(reqbody, &request)

	idval := request.Id
	amt := request.Amt

	fmt.Println(idval," ",amt)
	findOptions :=  bson.D{
        {"$set", bson.D{{"amt", amt}}},
    }
	filter := bson.M{"id": idval}
	_,err := collection.UpdateOne(context.TODO(), filter, findOptions)
	if err != nil {
		log.Fatal(err)
	}
	fmt.Fprintf(w,"Successfully updated amount")

}



func Deleteperson(w http.ResponseWriter,req *http.Request)  {

  clientOptions := options.Client().ApplyURI("mongodb://127.0.0.1:27017")
    client, err := mongo.Connect(context.TODO(), clientOptions)


   vars:=mux.Vars(req)
 idval:=vars["id"] 
   filter:=bson.M{"id":idval}

    if err != nil {
        log.Fatal(err)
    }

    err = client.Ping(context.TODO(), nil)

    if err != nil {
        log.Fatal(err)
    }

    fmt.Println("Connected to MongoDB!")

collection := client.Database("mydbb").Collection("payment")
   
   

     deleteResult, err := collection.DeleteMany(context.TODO(),filter /*bson.D{}*/)
  if err != nil {
      log.Fatal(err)
  }

  fmt.Printf("Deleted %v documents in the trainers collection\n", deleteResult.DeletedCount)

  err = client.Disconnect(context.TODO())

  if err != nil {
      log.Fatal(err)
  } else {
      fmt.Println("Connection to MongoDB closed.")
  }

fmt.Fprintf(w,"Deletion done")

}



func homelink(w http.ResponseWriter,req *http.Request)  {

	fmt.Fprintf(w,"my first rest go api")

	

}

func main()  {

     
	/*Allpeople= []person{
		person{Id:"1",Name:"something",City:"Somecity",Address:"cccs",Age:21},
		person{Id:"2",Name:"something",City:"Somecity",Address:"cccs",Age:21}}*/


	myrouter:= mux.NewRouter().StrictSlash(true)      //deserailize serialize


   myrouter.HandleFunc("/",homelink)  //by default get method

   myrouter.HandleFunc("/createpayment",createpersonmet).Methods("POST")
   myrouter.HandleFunc("/allpayment",Allperson)
   myrouter.HandleFunc("/getpayment/{id}",Getperson)
   myrouter.HandleFunc("/deletepayment/{id}",Deleteperson).Methods("DELETE")
   myrouter.HandleFunc("/updatepayment/{id}",Update).Methods("PUT")
   

	//http.HandleFunc("/",homelink)  //mapping bw the url and method
	http.ListenAndServe(":8997",myrouter)



}
