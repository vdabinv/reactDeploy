// ex2/Ex2_FlowReactStateDemo.tsx
import React, { useState } from 'react'
/*
    버튼을 클릭하면 랜더링은 한번만 일어 난다
    setState는 호출 즉시 렌더링을 하는 것이 아니라.
    동시에 변경되는 state들을 전부 누적한 후에 한꺼번에 랜더링이 일어 남 
*/
const Child = ({state,action}:any) => {
    return <button>{state}</button>
}
const Ex2_FlowReactStateDemo = () => {
   const [number1,setNumber1] = useState(0);
   const [number2,setNumber2] = useState(0);
   //함수도 정의
   const increment = () => {
        setNumber1((prev) => prev + 2);
        setNumber2((prev) => prev + 3); 
   }

   
   return (
    <div>
        <h1>Ex2_FlowReactStateDemo</h1>
        <Child state={number1}/>
        <Child state={number2}/>
        <button onClick={increment}>Change</button> 
    </div>
  )
}
export default Ex2_FlowReactStateDemo