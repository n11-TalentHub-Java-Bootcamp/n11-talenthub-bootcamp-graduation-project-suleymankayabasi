import React from "react";
import { useState } from "react";

const UpdateForm = () => {
	const [name, setName] = useState("");
	const [lastName, setLastName] = useState("");
	const [nationalId, setNationalId] = useState("");
	const [birthDate, setBirthDate] = useState("");
	const [phoneNumber, setPhoneNumber] = useState("");
	const [income, setIncome] = useState("");
	const [depositName, setDepositName] = useState("");
	const [depositAmount, setDepositAmount] = useState("");

	const handleSubmit = (e) => {
		e.preventDefault();

		const data = {
			name: name,
			lastName: lastName,
			nationalId: nationalId,
			birthDay: birthDate,
			phoneNumber: phoneNumber,
			income: income,
			creditScore: 0,
			depositDetailDTO: {
				nationalId: nationalId,
				name: depositName,
				amount: depositAmount,
			},
		};

		fetch(`http://localhost:8080/customer/update?nationalId=${nationalId}`, {
			method: "POST",
			headers: {
				"Content-Type": "application/json",
			},
			body: JSON.stringify(data),
		}).then((res) => {
      res.status === 200 ? alert("Kullanıcı Güncelleme Başarılı") : alert("Kullanıcı Güncelleme Başarısız");
		});
	};

	return (
		<div className='basis-5/6 bg-white rounded shadow-lg p-8 m-4'>
			<h1 className='block w-full text-center text-grey-darkest rounded-md font-bold uppercase bg-teal-500 py-2 mb-6'>
				Kredi Güncelleme Formu
			</h1>
			<form className='mb-4' action='/' method='post'>
				<div className='flex flex-col mb-6'>
					<label
						className='mb-2 uppercase font-bold text-lg text-grey-darkest'
						htmlFor='name'
					>
						Isim
					</label>
					<input
						className='border py-2 px-3 text-grey-darkest'
						type='text'
						name='name'
						id='name'
						value={name}
						onChange={(e) => setName(e.target.value)}
					/>
				</div>
				<div className='flex flex-col mb-6'>
					<label
						className='mb-2 uppercase font-bold text-lg text-grey-darkest'
						htmlFor='lastName'
					>
						Soyisim
					</label>
					<input
						className='border py-2 px-3 text-grey-darkest'
						type='text'
						name='lastName'
						id='lastName'
						value={lastName}
						onChange={(e) => setLastName(e.target.value)}
					/>
				</div>
				<div className='flex flex-col mb-4'>
					<label
						className='mb-2 uppercase font-bold text-lg text-grey-darkest'
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
				<div className='flex flex-col mb-4'>
					<label
						className='mb-2 uppercase font-bold text-lg text-grey-darkest'
						htmlFor='birthDate'
					>
						Doğum Tarihi
					</label>
					<input
						className='border py-2 px-3 text-grey-darkest'
						type='text'
						name='birthDate'
						id='birthDate'
						value={birthDate}
						onChange={(e) => setBirthDate(e.target.value)}
					/>
				</div>
				<div className='flex flex-col mb-4'>
					<label
						className='mb-2 uppercase font-bold text-lg text-grey-darkest'
						htmlFor='phoneNumber'
					>
						Telefon Numarası
					</label>
					<input
						className='border py-2 px-3 text-grey-darkest'
						type='text'
						name='phoneNumber'
						id='phoneNumber'
						value={phoneNumber}
						onChange={(e) => setPhoneNumber(e.target.value)}
					/>
				</div>
				<div className='flex flex-col mb-6'>
					<label
						className='mb-2 uppercase font-bold text-lg text-grey-darkest'
						htmlFor='income'
					>
						Aylık Gelir
					</label>
					<input
						className='border py-2 px-3 text-grey-darkest'
						type='text'
						name='income'
						id='income'
						value={income}
						onChange={(e) => setIncome(e.target.value)}
					/>
				</div>
				<div className='flex flex-col mb-6'>
					<label
						className='mb-2 uppercase font-bold text-lg text-grey-darkest'
						htmlFor='depositName'
					>
						Teminat Türü
					</label>
					<input
						className='border py-2 px-3 text-grey-darkest'
						type='text'
						name='depositName'
						id='depositName'
						value={depositName}
						onChange={(e) => setDepositName(e.target.value)}
					/>
				</div>
				<div className='flex flex-col mb-6'>
					<label
						className='mb-2 uppercase font-bold text-lg text-grey-darkest'
						htmlFor='depositAmount'
					>
						Teminat Miktarı
					</label>
					<input
						className='border py-2 px-3 text-grey-darkest'
						type='text'
						name='depositAmount'
						id='depositAmount'
						value={depositAmount}
						onChange={(e) => setDepositAmount(e.target.value)}
					/>
				</div>
				<button
					className='block bg-teal-500 hover:bg-teal-dark text-white uppercase text-lg mx-auto p-4 rounded'
					type='submit'
					onClick={(e) => {
						e.preventDefault();
						handleSubmit(e);
					}}
				>
					Güncelle
				</button>
			</form>
		</div>
	);
};

export default UpdateForm;
