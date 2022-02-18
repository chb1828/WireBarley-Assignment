import styles from '../styles/Home.module.css'

export default function Home() {

    const onChange = async (e) => {

    }

  return (
      <div>
        <h1>환율 계산</h1>
        <p>송금국가: 미국(USD)</p>
          <div>
              <span>수취국가: </span>
              <select>
                  <option>한국(KRW)</option>
                  <option>일본(KRW)</option>
                  <option>필리핀(KRW)</option>
              </select>
          </div>
        <p>환율: 1,119.93 KRW/USD</p>
        <div>
            <form>
                <span>송금액: </span><input type="number"/><span><strong>USD</strong></span><br/>
                <button className={styles.submitBtn}><strong>Submit</strong></button>
            </form>
        </div>
      </div>
  )
}
