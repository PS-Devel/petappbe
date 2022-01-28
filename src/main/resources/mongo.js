use petapp
db.createCollection('users');
db.profiles.insertMany([
   { profile: "PROFILE_BASIC" },
   { profile: "PROFILE_BUSINESS" }])
db.users.insertOne(
   {
    "username": "test-user-uno",
    "email": "l.stagliano@gmail.com",
    "password": "teseS!!3"
});
db.users.insertOne(
   {
    "username": "test-user-due",
    "email": "tnprR7igyawww@mail.it",
    "password": "testeeSO!"
});
db.users.insertOne(
   {
    "username": "test-user-tre",
    "email": "tnprR7ioPvgww@mail.it",
    "password": "testeeS!3"
});