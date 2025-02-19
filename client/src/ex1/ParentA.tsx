// ex1/ParentA.tsx
//rafce
import React from 'react'
import ChildC from './ChildC';

const ParentA = () => {
  const dataA = "부모A의 데이터 A";
  const dataB = "부모A의 데이터 B";
  return (
   <ChildC propA={dataA} propB={dataB} />
  )
}

export default ParentA