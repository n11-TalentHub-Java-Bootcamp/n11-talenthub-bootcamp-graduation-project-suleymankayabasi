import React, { useState } from 'react';

const DeleteForm = () => {

  const [nationalId, setNationalId] = useState("");
	const [birthDay, setBirthDay] = useState("");

  const handleQuery = (e) => {
		e.preventDefault();

		fetch(
			`http://localhost:8080/customer/delete?nationalId=${nationalId}&birthDay=${birthDay}`,
			{
				method: "GET",
			}
		).then((data) => {
      data.status === 200 ? alert("Silme işlemi başarılı") : alert("Silme işlemi başarısız");
    }).catch((error) => {
      console.log(error);
    })
	};

  return (<div className='bg-white min-h-screen w-full flex flex-col'>
			<form className='mx-auto mt-40'>
      <h1 className='block w-full text-center text-grey-darkest rounded-md font-bold uppercase bg-teal-500 py-2 mb-6'>
				Kullanıcı Silme
			</h1>
				<div className='flex flex-col mb-6'>
					<label
						className='mb-2 mr-2 uppercase font-bold text-lg text-grey-darkest'
						htmlFor='nationalId'
					>
						T.C. Kimlik No
					</label>
					<input
						className='border py-2 px-3 text-grey-darkest'
						type='text'
						name='nationalId'
						id='nationalId'
						value={nationalId}
						onChange={(e) => setNationalId(e.target.value)}
					/>
				</div>
				<div className='flex flex-col mb-6'>
					<label
						className='mb-2 mr-2 uppercase font-bold text-lg text-grey-darkest'
						htmlFor='birthDay'
					>
						Doğum Tarihi
					</label>
					<input
						className='border py-2 px-3 text-grey-darkest'
						type='text'
						name='birthDay'
						id='birthDay'
						value={birthDay}
						onChange={(e) => setBirthDay(e.target.value)}
					/>
				</div>
				<button
					className='block bg-teal-500 hover:bg-teal-dark text-white uppercase text-lg mx-auto p-4 rounded'
					type='submit'
					onClick={(e) => {
						handleQuery(e);
					}}
				>
					Kullanıcı Sil
				</button>
			</form>
		</div>
	);
};

export default DeleteForm;
