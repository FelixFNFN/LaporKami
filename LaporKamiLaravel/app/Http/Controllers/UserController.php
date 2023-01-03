<?php

namespace App\Http\Controllers;

use App\Models\Users;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Hash;

class UserController extends Controller
{
    public function insertUser(Request $request)
    {
        return view('UserView');
    }

    function RegisterUser(Request $request)
    {
        $user = Users::create(array(
            "email" => $request->email,
            "nama" => $request->nama,
            "noTelp" => $request->noTelp,
            // "password" => Hash::make($request->password) ,
            "password" => $request->password ,
        ));
        return response()->json($user, 201);

    }

    function LoginUser(Request $request)
    {
        return response()->json(Users::where('email', $request->email)->get(), 200);
    }
}
