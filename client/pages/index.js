import styles from '../styles/Home.module.css';
import axios from "axios";
import {useEffect, useState} from "react";

export default function Home() {

    const [apiResult,setApiResult] = useState("");
    const [country, setCountry] = useState("krw");
    const [result, setResult] = useState("");

    const onChange = async e => {
        setCountry(e.target.value);
        setResult("");
    }

    const onSubmit = e => {
        e.preventDefault();
        let number = e.target[0].value;
        let value = calc(apiResult,number);
        setResult(value);
    }

    const calc = (apiResult, number) => {
        return (Number(apiResult) * Number(number)).toFixed(2);
    }

    //useEffect는 비동기적으로 동작
    useEffect(() => {
        let completed = false; //초기에는 실행해야 되기때문에 false flag 변수

        //query를 리턴하는 함수를 result에 할당
        async function get() {
            const result = await axios.get(
                `/api?param=${country}`
            );
            if (!completed) setApiResult(result.data);
        }
        get().catch(function(error) {
            console.log(error);
        });
        return () => {
            completed = true;
        };

    }, [country]);


  return (
      <div>
        <h1>환율 계산</h1>
        <p>송금국가: 미국(USD)</p>
          <div>
              <span>수취국가: </span>
              <select onChange={onChange} value={country}>
                  <option value="krw">한국(KRW)</option>
                  <option value="jpy">일본(JPY)</option>
                  <option value="php">필리핀(PHP)</option>
              </select>
          </div>
        <p>환율: {apiResult} {country}/USD</p>
        <div>
            <form onSubmit={onSubmit}>
                <span>송금액: </span><input type="number" name="number"/><span><strong>USD</strong></span><br/>
                <button className={styles.submitBtn}><strong>Submit</strong></button>
            </form>
        </div>
          <hr/>
          <h3>{result ? `수취금액은 ${result} ${country.toUpperCase()} 입니다.` : ""}</h3>
      </div>
  )
}
