// ex1/ParentB.tsx
//rafce
import React from 'react'
import ChildC from './ChildC';


const ParentB = () => {
  const dataA = "부모B의 데이터 A";
  const dataC = "부모B의 데이터 C";
  return (
      <ChildC propA={dataA} propC={dataC} />
    // 여기서 propA를 전달하지 않아서 오류가 발생.
    // <ChildC propC={dataC} />
  )
}

export default ParentB