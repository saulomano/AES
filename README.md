# AES
Migrated Visual Basic AES crypt/decrypt function to Java
Two functions that encrypt/decrypt strings as the original visual basic code pasted below.

Technical solution:
Solution provided is J2SE, and it uses javax.crypto and java.security packages. Solution runs with JDK 7. You don't need any J2EE library for run it. Solution requires JCE and an Apache library.

Just for curiosity the original visual basic code has a bug. The position 15 of the array hash is overwritten, and the last one (position 31) is never written. I will keep this bug in my java code, because if not the functions will not work as visual basic ones.

Array.Copy(temp, 0, hash, 0, 16) > Here you write from 0 to 15 position (16 characters)
Array.Copy(temp, 0, hash, 15, 16) > Here is the bug. You start writing in position 15 instead of 16.

Original visual basic code:
```vbs
Namespace EncryptionAes
Imports Microsoft.VisualBasic
Imports System
Imports System.IO
Imports System.Security.Cryptography
Imports System.Text

Public Class Encryption
    Public Shared pass As String = "SingleSignOnCryp"
 
    Public Shared Function AES_Encrypt(ByVal input As String) As String
        Dim AES As New System.Security.Cryptography.RijndaelManaged
        Dim Hash_AES As New System.Security.Cryptography.MD5CryptoServiceProvider
        Dim encrypted As String = ""
        Try
            Dim hash(31) As Byte
            Dim temp As Byte() = Hash_AES.ComputeHash(System.Text.ASCIIEncoding.ASCII.GetBytes(pass))
            Array.Copy(temp, 0, hash, 0, 16)
            Array.Copy(temp, 0, hash, 15, 16)
            AES.Key = hash
            AES.Mode = CipherMode.ECB
            Dim DESEncrypter As System.Security.Cryptography.ICryptoTransform = AES.CreateEncryptor
            Dim Buffer As Byte() = System.Text.ASCIIEncoding.ASCII.GetBytes(input)
            encrypted = Convert.ToBase64String(DESEncrypter.TransformFinalBlock(Buffer, 0, Buffer.Length))
            Return encrypted
        Catch ex As Exception
            Return Nothing
        End Try
 
    End Function
 
    Public Shared Function AES_Decrypt(ByVal input As String) As String
        Dim AES As New System.Security.Cryptography.RijndaelManaged
        Dim Hash_AES As New System.Security.Cryptography.MD5CryptoServiceProvider
        Dim decrypted As String = ""
        Try
            Dim hash(31) As Byte
            Dim temp As Byte() = Hash_AES.ComputeHash(System.Text.ASCIIEncoding.ASCII.GetBytes(pass))
            Array.Copy(temp, 0, hash, 0, 16)
            Array.Copy(temp, 0, hash, 15, 16)
            AES.Key = hash
            AES.Mode = CipherMode.ECB
            Dim DESDecrypter As System.Security.Cryptography.ICryptoTransform = AES.CreateDecryptor
            Dim Buffer As Byte() = Convert.FromBase64String(input)
            decrypted = System.Text.ASCIIEncoding.ASCII.GetString(DESDecrypter.TransformFinalBlock(Buffer, 0, Buffer.Length))
            Return decrypted
        Catch ex As Exception
            Return Nothing
        End Try
    End Function
End Class
End Namespace
```vbs
