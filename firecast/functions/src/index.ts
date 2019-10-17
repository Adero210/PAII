import * as functions from 'firebase-functions';
// // Start writing Firebase Functions
// // https://firebase.google.com/docs/functions/typescript
//export const getnot= functions.https.onRequest((request, response) => {
//    console.log('Hello!');
//  response.send("Hello from Firecast!!");
//});

export const notification = functions.database
.ref('not/').onCreate((snapshot,context) =>{

  const countDays = snapshot.ref.child('dia') 
  console.log(countDays)
  return snapshot.ref.update({text:})

}



/*export const onMessageCreate = functions.database
.ref('/not/{notid}')
.onCreate((snapshot,context) => {

console.log('user = ${notid}')

const messageData = snapshot.val();

const text = addValue(messageData.text)
return snapshot.ref.update({text: text})

})


function addValue(text: string):string{

  return text.replace(/\b0\b/g,'1')
}

export const onMessageUpdate = functions.database
.ref('/not/{notid}')
.onUpdate((change,context) => {
  const before = change.before.val()
  const after = change.after.val()

  if(before.text === after.text){
  console.log("text didný change")
  return null
}

  const text = addValue(after.text)
  const timeEdited = Date.now()
  return change.after.ref.update({text, timeEdited})

})*/


