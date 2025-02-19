// ex2/UseStateDemo1.tsx
import React, { useState } from 'react'

const UseStateDemo1 = () => {
  /*  
  const n = [1,2,3];
  console.log(typeof(n));
  console.log(n);
  //배열의 값을 따로 상수던 변수던 저장 
  const fn = n[0];
  const sn = n[1];
  const tn = n[2];
  console.log(fn, sn, tn);
  const person = {name:'Tess',age:20};
  const name = person.name; 
  const age = person.age;
  */
  //축약형 문법 *****
  //const [fn,sn,tn] = [1,2,3];
  //const {name,age} = {name:'Tess',age:20}; 
  // useState 훅을 축약형으로 선언 
  // const [필드,setter] = useState(0);
  const [count, setCount] = useState(0);
  console.log("useState 데모!"); 
  // 함수 정의 
  //const 함수이름 = () => {} 
  // 버튼이 클릭이 될 때 useState에 선언한 함수를 호출해서
  // 저장해둔 count에 1을 더한다.
  const clickedIncrement = () => {
    setCount(count + 1);
  }; 
  return (
    <div>
        <h1>UseStateDemo1</h1>
        <p>현재 카운트 : {count}</p>
        <button onClick={clickedIncrement}>증가시키기</button>
    </div>
  )
}
export default UseStateDemo1