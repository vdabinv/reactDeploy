// ex3/Ex1_UseEffectDemo.tsx

import React, { useState } from 'react'

const Ex1_UseEffectDemo = () => {
 // 서버로 부터 response 받을 데이터는 data자체는 object이다. null
  const [data, setData] = useState(null);
// data.message => 문자열 
  const [message, setMessage] = useState('');  
  
  //함수 만들기 - axios는 아직 설치 안했다. fetch함수로 받을 수 있다.
  // met
  /*
  {
  "msg": "안녕하세요 스프링 부트와 React입니다."
  }
  */
  const fetchData = () => {
      fetch('http://192.168.0.42/myapp0719/hello')
        .then(response => response.json()) // json타입을 자바스크립트 data로 변환
        .then(data => setMessage(data.msg)) // 받은 data.msg를 useState훅에 저장 
        .catch(error => console.error('Error fetching data:', error));
  };
  return (
    <div>
        
        <h1>Ex1_UseEffectDemo</h1>
        <button onClick={fetchData}>메세지받기</button>
        <div>{message? message : '아직 메시지를 받지 못함!'}</div>
    </div>
  )
}

export default Ex1_UseEffectDemo