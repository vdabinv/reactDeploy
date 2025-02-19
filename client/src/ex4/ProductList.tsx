



//ex4/ProductList.tsx
// Map,전개연산자 복습
import React, { useEffect, useState } from 'react'

interface ProductType {
  id: number;
  name: string;
  context: string;
  price: number;
  createdAt: Date;   // 생성 날짜
  updatedAt: Date;   // 수정 날짜
  discount: number;  // 할인율 (소수점 포함 가능)
  rating: number;    // 평점 (소수점 포함 가능)
}
const ProductList = () => {

  //useState선언
  const [products, setProducts] = useState<ProductType[]>([]);
  const [loading, setLoading] = useState<boolean>(false);
  useEffect(() => {
    const fetchUserData = async() => {
      setLoading(true);
     //예외 문법 try ~ catch ~ finally 
     try{
      const response = await fetch('http://192.168.0.42/myapp0719/product');
      const data = await response.json();
      // ajax로 받아온 데이터 중에 날짜만 타입을 변경해서 다시 수정한다.
      // 별도의 ProductType 의 구현 object를 사용
      console.log("Data =>", data);
      const productData : ProductType = {
           ...data,
           createdAt: new Date(data.createdAt),
           updatedAt: new Date(data.updatedAt),
      }
      // 변경된 자바스크립트 Object인 productData 를 state에 저장
      // type에 맞게 배열로 만들어서 저장한다.
      setProducts([productData]);
     }catch(error){
        console.error(`에러가 났음 ${error}`);
     }finally{
          setLoading(false);
          console.log("products=====================");

     }
    };
    fetchUserData(); // 함수 호출!

    // // map의 개념 파악해보기
    // const arr = [1, 2, 3, 4, 5, 6, 7];
    // console.log(typeof(arr));
    // // 전개연산자 ... 
    // const newArr = [...arr, 8, 9, 10]; // 배열의 끝에 8, 9, 10을 추가
    // console.log(newArr); // [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
    // const newArr2 = [0,...arr]; // 배열의 앞에 0을 추가
    // console.log(newArr2);
    // // map => for문 없이 배열을 순회하면서 적용
    // console.log(newArr.map(item => item.toString()));
    // console.log(newArr2.map((x) => x * 2)); //for문 없이 연산
  }, []);
  if (loading) {
    return <div>Loding...</div>
  }
  return (
    <div>
      <h1>ProductList :</h1>
      <h2>배열의 요소를 사용</h2>
      {products.length > 0 ? (
        <>
          <p >{products[0].id}</p>
          <p>{products[0].name}</p>
          <p>{products[0].price}</p>
          <p>{products[0].context}</p>
        </>
      ):(<p>데이터가 존재하지 않음</p>)} 
      <h2>map 사용</h2>
      {/* * Key는 리액트에서 어떤 항목을 변경, 추가 또는 삭제 할지 식별할때 사용하기 위해서 지정하는 것이다. */}
      {products.map(product => (
        <div key={product.id}>
          <p>{product.id}</p>
          <p>{product.name}</p>
          <p>{product.price}</p>
          <p>{product.context}</p>
          <p>Created At: {product.createdAt.toLocaleDateString()}</p>
          <p>Updated At: {product.updatedAt.toLocaleDateString()}</p>
          <p>Discount: {product.discount.toFixed(2)}%</p>
          <p>Rating: {product.rating.toFixed(2)} 점</p>
        </div>
      ))}
    </div>
  )
}

export default ProductList