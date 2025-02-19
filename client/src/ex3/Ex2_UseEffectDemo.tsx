//Ex2_UseEffectDemo.tsx
//rafce
import React, { useEffect, useRef, useState } from 'react'

const Ex2_UseEffectDemo = () => {
 // 서버로 부터 response 받을 데이터는 data자체는 object이다. null
 const [data, setData] = useState(null);
 // data.message => 문자열 
   const [message, setMessage] = useState('');  
   const titleRef = useRef<HTMLInputElement>(null);

const fetchData = () => {
    if (titleRef.current){
    //input의 값을 받아서 상수에 저장한다.
    const title = titleRef.current.value;
    console.log("title=>", title);
    fetch(`http://192.168.0.42/myapp0719/hello?title=${title}`)
      .then(response => response.json()) // json타입을 자바스크립트 data로 변환
      .then(data => setMessage(data.msg)) // 받은 data.msg를 useState훅에 저장 
      .catch(error => console.error('Error fetching data:', error));
    }
};
//<연습문제>
// useEffect를 사용해서 title에 기본메세지라고 전송을 한 후에 
// 컴포넌트가 마운트 될 때 기본데이터를 가져와서 랜더링 시켜보시오.
//ufe
useEffect(() => {
    let titleloc = "기본메세지";
    fetch(`http://192.168.0.42/myapp0719/hello?title=${titleloc}`)
    .then(response => response.json()) // json타입을 자바스크립트 data로 변환
    .then(data => {
    setMessage(data.msg)
    setData(data);
    console.log(data);
    }
    ) // 받은 data.msg를 useState훅에 저장 
    .catch(error => console.error('Error fetching data:', error));
}, []);
  return (
    <div>
        <input 
          type="text" 
          id="msgv"
          ref={titleRef}
          placeholder="Enter title"
        />
        <button onClick={fetchData}>Fetch Message</button>
        <div>{message ? message : 'No message yet'}</div>
      </div>
  )
}

export default Ex2_UseEffectDemo