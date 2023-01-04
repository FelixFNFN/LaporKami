<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Questions extends Model
{
    use HasFactory;
    protected $table = "questions";
    protected $primaryKey = "id";
    protected $fillable = ["pertanyaan"];
    public $timestamps = false;


    // public function users()
    // {
    //     return $this->belongsTo(Users::class, 'id','id');
    // }
}
