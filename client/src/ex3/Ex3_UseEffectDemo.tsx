

//Ex3_UseEffectDemo.tsx
import React, { useEffect, useState } from 'react'

function Ex3_UseEffectDemo() {
const [count, setCount] = useState(0);
useEffect(() => {
    //이 함수는 컴포넌트가 랜더링 될 때마다 호출 됨.
    //브라우저의 제목을 의미함 <title>에 해당 :document.title
    document.title = `You Clicked ${count} times`;
  },[]); // 만약 []에서 count를 연결하면 count값이 변경될 때 마다 호출이 되는 것을 확인 
  return (
    <div> <h2>Ex3_UseEffectDemo</h2>
    <p>You Clicked 화면 {count} times</p>
    <button onClick={() => setCount(count + 1)}>Clicked</button></div>
  )
}

export default Ex3_UseEffectDemo