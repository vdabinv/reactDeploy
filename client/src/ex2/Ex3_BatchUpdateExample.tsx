// ex2/Ex3_BatchUpdateExample.tsx
import React, { useEffect, useState } from 'react'
// 요점 > handleClick 이벤트가 발생할 때 로그를 보면
// 전 , 후에 state값들이 반영이 안됨을 확인하고
// 한꺼번에 랜더링 되는 브라우저의 증가값을 확인 한다.
function Ex3_BatchUpdateExample() {
  const [state1, setState1] = useState(0);
  const [state2, setState2] = useState(0); 
  //met
 //handleClick 함수에서 setState1과 setState2를 호출하지만, 이때는 상태가 아직 업데이트되지 않
//는다.
  const handleClick  = () => {
    //setState1과 setState2를 호출하여 상태를 각각 1씩 증가
    console.log('Before setState1:', state1);
    setState1(state1 + 1);
    console.log('After setState1:', state1);
    console.log('Before setState2:', state2);
    setState2(state2 + 1);
    console.log('After setState2:', state2);
  }
  //컴포넌트가 렌더링될 때마다 console.log('Rendering...')가 호출
  console.log('Rendering...');

  //uef
 //렌더링이 발생한 후에야 useEffect 훅이 호출되며, 이때 업데이트된 상태 값이 반영
// useEffect 훅을 사용하여 상태가 실제로 업데이트된 시점을 확인할 수 있다아!!!
  useEffect(() => {
    console.log("State1 값 업데이트 :",state1)
  }, [state1]);
  useEffect(() => {
    console.log("State2 값 업데이트 :",state1)
  }, [state2]);
  return (
    <div>
        <h1>Ex3_BatchUpdateExample</h1>
        <p>State 1: {state1}</p>
        <p>State 2: {state2}</p>
        <button onClick={handleClick}>Update States</button>
    </div>
  )
}

export default Ex3_BatchUpdateExample