

//Ex4_UseEffectDemo.tsx
/*
https://jsonplaceholder.typicode.com/users/1
{
  "id": 1,
  "name": "Leanne Graham",
  "username": "Bret",
  "email": "Sincere@april.biz",
  "address": {
    "street": "Kulas Light",
    "suite": "Apt. 556",
    "city": "Gwenborough",
    "zipcode": "92998-3874",
    "geo": {
      "lat": "-37.3159",
      "lng": "81.1496"
    }
  },
  "phone": "1-770-736-8031 x56442",
  "website": "hildegard.org",
  "company": {
    "name": "Romaguera-Crona",
    "catchPhrase": "Multi-layered client-server neural-net",
    "bs": "harness real-time e-markets"
  }
}
*/
import React, { useEffect, useState } from 'react'
interface Geo {
    lat: string;
    lng: string;
  }

interface Address {
    street: string;
    suite: string;
    city: string;
    zipcode: string;
    geo: Geo;
}
interface Company {
    name: string;
    catchPhrase: string;
    bs: string;
}
//jsonObject를 받아오기 위한 typescript의 interface 선언
interface UserData{
    id: number;
    name: string;
    username: string;
    email: string;
    phone: string;
    website: string;
    company:Company;
    address: Address;
}

const Ex4_UseEffectDemo = () => {
    // userData 상태의 타입을 UserData | null로 명시
  const [userData,setUserData] = useState<UserData | null>(null);
  const [loading,setLoading] = useState(false);
  // 처음 마운트 될 때 데이터를 읽어 들여서 랜더링을 초기화 
  useEffect(() => {
    const fetchUserData = async() => {
        setLoading(true);
        const response = await fetch("https://jsonplaceholder.typicode.com/users/1");
        //response 객체의 json 메서드를 호출하여 응답 데이터를 JSON 형식으로 변환하는데
        // 마찬가지로 await 때문에 변환이 끝날때 까지 대기한다.
        const data = await response.json();
        setUserData(data);
        setLoading(false);
    };
    fetchUserData(); // 함수 호출!
  }, []); // []  마운트 시 한 번만 실행
  if (loading) {
    return <div>Loding...</div>
  }
  return (
    <div>
        <h1>Ex4_UseEffectDemo</h1>
        {userData && (
            <>
                <p>Name : {userData.name}</p>
                <p>Username: {userData.username}</p>
                <p>Email: {userData.email}</p>
                <p>Phone: {userData.phone}</p>
                <p>Website: {userData.website}</p>
                <p>Company: {userData.company.name} - {userData.company.catchPhrase}</p>
                <p>Address: {userData.address.street}, {userData.address.suite}, 
                    {userData.address.city}, {userData.address.zipcode}</p>
            </>
        )}
    </div>
  )
}

export default Ex4_UseEffectDemo